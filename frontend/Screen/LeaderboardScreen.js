// LeaderboardScreen.js
import React, { useState, useContext, useEffect } from 'react';
import {
  View,
  Text,
  Image,
  ImageBackground,
  StyleSheet,
  TouchableOpacity,
  ScrollView,
  Platform,
} from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { fetchLeaderboard } from '../service/api';
import { PointsContext } from '../context/PointsContext';

// Stage 0 and 1 use the same image; Stage 2 uses the full score image
const CARD_IMAGES = [
  require('../assets/Lead/card1.png'),
  require('../assets/Lead/card1.png'),
  require('../assets/Lead/card2.png'),
];

// Default avatar placeholder
const DEFAULT_AVATAR = require('../assets/Lead/Head.png');

// ✅ Community icon: replace the path with your image
const COMMUNITY_ICON = require('../assets/Lead/map-pin.png');

export default function LeaderboardScreen() {
  // Points and task progress from global context
  const { points, taskProgress, user } = useContext(PointsContext);
  const [cardH, setCardH] = useState(0); // Record the actual height of the card to position the button
  const navigation = useNavigation();

  // Leaderboard top tabs to switch between Day/Week/All
  const [selectedTab, setSelectedTab] = useState('Day');
  const [leaderboardUsers, setLeaderboardUsers] = useState([]);

  const isFull = taskProgress >= 2;

  const onPressComplete = () => {
    navigation.navigate('Tasks');
  };

  // Switch scores and rankings based on the selected time range
  const pointsField =
    selectedTab === 'Day'
      ? 'dailyPoints'
      : selectedTab === 'Week'
      ? 'weeklyPoints'
      : 'totalPoints';

  useEffect(() => {
    if (!user) return;
    const period =
      selectedTab === 'Day'
        ? 'daily'
        : selectedTab === 'Week'
        ? 'weekly'
        : 'all';
    fetchLeaderboard(user.id, period)
      .then(setLeaderboardUsers)
      .catch(() => setLeaderboardUsers([]));
  }, [selectedTab, user]);

  let listData = [];
  if (user) {
    const combined = [
      user,
      ...leaderboardUsers.filter((u) => u.id !== user.id),
    ]
      .sort((a, b) => (b[pointsField] || 0) - (a[pointsField] || 0))
      .slice(0, 10);

    const nameCount = {};
    listData = combined.map((u) => {
      const baseName = u.username || 'User';
      const count = nameCount[baseName] || 0;
      nameCount[baseName] = count + 1;
      return {
        ...u,
        username: count ? `${baseName} ${count + 1}` : baseName,
      };
    });
  }

  return (
    <ImageBackground
      source={require('../assets/Lead/bg.png')}
      style={[styles.screenBg, Platform.OS === 'web' && styles.screenBgWeb]}
      imageStyle={styles.screenBgImage}
    >
      <ScrollView style={styles.container} contentContainerStyle={styles.content}>
        {/* Top statistics */}
        <View style={styles.header}>
          {/* Image + Text: Community row */}
          <View style={styles.communityRow}>
            <Image source={COMMUNITY_ICON} style={styles.communityIcon} />
            <Text style={styles.communityText}>
              {user?.community || 'UCC Community'}
            </Text>
          </View>

          <View style={styles.pointRow}>
            {/* ✅ Left: Display "My point" label first, then show the large points value */}
            <View style={styles.pointLeft}>
              <Text style={styles.pointsLabel}>My point</Text>
              <Text style={styles.myPoints}>{points}</Text>
            </View>

            <View style={styles.badgeRow}>
              {/* Removed the left Day badge, only keeping Exchange */}
              <TouchableOpacity
                onPress={() => navigation.navigate('Rewards')}
                style={[styles.badge, styles.exchangeBadge]}
              >
                <Text style={[styles.badgeText, styles.exchangeBadgeText]}>
                  Exchange &gt;
                </Text>
              </TouchableOpacity>
            </View>
          </View>
        </View>

        {/* ===== Single task card: Image + Button with absolute positioning ===== */}
        <ImageBackground
          source={CARD_IMAGES[taskProgress]}
          style={styles.taskCardBg}
          imageStyle={styles.taskCardBgImage}
          onLayout={(e) => setCardH(e.nativeEvent.layout.height)}
        >
          <TouchableOpacity
            activeOpacity={0.9}
            onPress={onPressComplete}
            style={[
              styles.cardButton,
              { top: cardH * 0.42 }, // Adjust vertical position proportionally
              isFull && styles.cardButtonFull,
            ]}
          >
            <Text style={styles.cardButtonText}>
              {isFull ? 'More Points' : 'Complete Task'}
            </Text>
          </TouchableOpacity>
        </ImageBackground>
        {/* ===== End of task card ===== */}

        {/* Leaderboard Tabs (Keep Day / Week / All) */}
        <View style={styles.tabRow}>
          <Text style={styles.leaderboardTitle}>Leaderboard</Text>
          <View style={styles.tabButtons}>
            {['Day', 'Week', 'All'].map((tab) => (
              <TouchableOpacity
                key={tab}
                onPress={() => setSelectedTab(tab)}
                hitSlop={{ top: 6, bottom: 6, left: 6, right: 6 }}
              >
                <Text
                  style={[
                    styles.tabText,
                    selectedTab === tab && styles.tabTextSelected,
                  ]}
                >
                  {tab}
                </Text>
              </TouchableOpacity>
            ))}
          </View>
        </View>

        {/* Leaderboard list (Left: Rank + Avatar + Name + Points) */}
        <View style={styles.listContainer}>
          {listData.map((item, index) => {
            const avatarSource = item.avatarUrl
              ? { uri: item.avatarUrl }
              : DEFAULT_AVATAR;

            return (
              <View
                key={item.id ?? index}
                style={[
                  styles.userCard,
                  item.id === user?.id && styles.highlightCard,
                ]}
              >
                <Text style={styles.rank}>{index + 1}</Text>
                <Image source={avatarSource} style={styles.avatar} />

                <Text style={styles.username} numberOfLines={1}>
                  {item.username || 'User'}
                </Text>

                <Text style={styles.points}>{item[pointsField]}</Text>
              </View>
            );
          })}
        </View>

        <TouchableOpacity style={styles.moreBtn}>
          <Text style={styles.moreText}>More Residents &gt;</Text>
        </TouchableOpacity>
      </ScrollView>
    </ImageBackground>
  );
}

/* ---------------- styles ---------------- */

const CARD_HEIGHT = 100; // Design card height; adjust here if needed

const styles = StyleSheet.create({
  screenBg: { flex: 1 },
  screenBgWeb: { width: '100%', height: '100%' },
  screenBgImage: { resizeMode: 'cover' },

  container: {
    flex: 1,
    backgroundColor: 'transparent',
    ...(Platform.OS === 'web' && { width: '100%', maxWidth: 480, alignSelf: 'center' }),
  },
  content: {
    padding: 20,
    paddingBottom: 40,
  },

  header: { marginBottom: 20 },

  /* Community row styles */
  communityRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
  },
  communityIcon: {
    width: 40,     // Enlarged size
    height: 40,
    resizeMode: 'contain',
  },
  communityText: { fontSize: 16, color: '#666' },

  pointRow: {
    marginTop: 10,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
  },

  /* ✅ Added: Left "My Points" container and label styles */
  pointLeft: {
    flexDirection: 'column',
  },
  pointsLabel: {
    fontSize: 14,
    color: '#6b7280', // Gray tone (similar to Tailwind gray-500)
    fontWeight: '600',
    marginBottom: 2,
  },
  myPoints: { fontSize: 36, fontWeight: 'bold', color: '#222' },

  badgeRow: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  badge: {
    backgroundColor: '#f1f5f9',
    borderRadius: 16,
    paddingHorizontal: 10,
    paddingVertical: 6,
    marginLeft: 8,
  },
  badgeText: { color: '#111', fontWeight: '600', fontSize: 12 },
  exchangeBadge: {
    backgroundColor: '#fde047',
  },
  exchangeBadgeText: {
    color: '#000',
  },

  /* Task card */
  taskCardBg: {
    width: '100%',
    height: CARD_HEIGHT,
    overflow: 'hidden',
    marginBottom: 20,
    borderRadius: 24, // Remove if your image already has rounded corners
  },
  taskCardBgImage: {
    resizeMode: 'cover',
  },

  cardButton: {
    position: 'absolute',
    right: 2,               // More to the right
    paddingHorizontal: 10,  // Smaller
    paddingVertical: 8,     // Smaller
    borderRadius: 24,
    backgroundColor: '#22c55e',
    alignItems: 'center',
    justifyContent: 'center',
    minWidth: 80,

    shadowColor: '#000',
    shadowOpacity: 0.15,
    shadowRadius: 8,
    shadowOffset: { width: 0, height: 4 },
    elevation: 4,
  },
  cardButtonFull: {
    backgroundColor: '#22c55e',
  },
  cardButtonText: {
    color: '#fff',
    fontWeight: '700',
    fontSize: 11,
  },

  /* Tabs & list */
  tabRow: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginTop: 8,
    marginBottom: 10,
  },
  leaderboardTitle: { fontSize: 22, fontWeight: '800', color: '#111' },
  tabButtons: { flexDirection: 'row', alignItems: 'center', gap: 16 },
  tabText: { fontSize: 16, color: '#9ca3af' },
  tabTextSelected: {
    color: '#111',
    fontWeight: '700',
    textDecorationLine: 'underline',
  },

  listContainer: { marginTop: 10 },
  userCard: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 12,
    borderBottomWidth: 1,
    borderBottomColor: '#eee',
    gap: 10,
  },
  highlightCard: {
    backgroundColor: '#e0f7fa',
    borderRadius: 12,
    paddingHorizontal: 10,
  },

  avatar: {
    width: 36,
    height: 36,
    borderRadius: 18,
    backgroundColor: '#e5e7eb', // Placeholder background color
  },
  rank: {
    width: 24,
    textAlign: 'center',
    fontWeight: '700',
    fontSize: 16,
    color: '#111',
  },
  username: { flex: 1, fontSize: 16, color: '#111' },
  points: { fontSize: 16, fontWeight: '600', color: 'green' },

  moreBtn: { marginTop: 16, alignItems: 'center' },
  moreText: { color: '#888', fontSize: 14 },
});
