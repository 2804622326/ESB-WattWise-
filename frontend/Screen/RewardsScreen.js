// RewardsScreen.js
import React, { useContext, useState, useEffect } from 'react';
import {
  View,
  Text,
  StyleSheet,
  FlatList,
  Alert,
  ImageBackground,
  Platform,
} from 'react-native';
import RewardCard from '../components/RewardCard';
import { PointsContext } from '../context/PointsContext';
import { fetchRewards, redeemReward } from '../service/api';

const REWARD_IMAGES = {
  1: require('../assets/Reward/1.png'),
  2: require('../assets/Reward/2.png'),
  3: require('../assets/Reward/3.png'),
  4: require('../assets/Reward/4.png'),
  5: require('../assets/Reward/5.png'),
  6: require('../assets/Reward/6.png'),
};

const RewardsScreen = () => {
  const isWeb = Platform.OS === 'web';
  const { points, deductPoints, user } = useContext(PointsContext);
  const [rewardItems, setRewardItems] = useState([]);

  useEffect(() => {
    fetchRewards()
      .then((data) =>
        setRewardItems(
          data.map((r) => ({
            ...r,
            image: REWARD_IMAGES[r.id] || (r.imageUrl ? { uri: r.imageUrl } : null),
          }))
        )
      )
      .catch(() => setRewardItems([]));
  }, []);

  const handleExchange = (item) => {
    if (points >= item.costPoints) {
      deductPoints(item.costPoints);
      if (user) {
        redeemReward(user.id, item.id).catch(() => {});
      }
      const msg = `You have redeemed: ${item.name}`;
      if (Platform.OS === 'web') {
        window.alert(`Success\n${msg}`);
      } else {
        Alert.alert('Success', msg);
      }
    } else {
      const err = 'You do not have enough points to redeem this item.';
      if (Platform.OS === 'web') {
        window.alert(`Not Enough Points\n${err}`);
      } else {
        Alert.alert('Not Enough Points', err);
      }
    }
  };

  return (
    <ImageBackground
      source={require('../assets/Lead/bg.png')}
      style={[styles.bg, isWeb && styles.bgWeb]}
      imageStyle={styles.bgImg}
    >
      <View style={styles.container}>
        <Text style={styles.header}>Exchange List</Text>

        <FlatList
          data={rewardItems}
          numColumns={2}
          keyExtractor={(item) => item.id.toString()}
          columnWrapperStyle={styles.row}
          renderItem={({ item }) => (
            <RewardCard item={item} onExchange={() => handleExchange(item)} />
          )}
          contentContainerStyle={styles.listContent}
          showsVerticalScrollIndicator={false}
        />
      </View>
    </ImageBackground>
  );
};

const styles = StyleSheet.create({
  bg: { flex: 1 },
  bgWeb: { width: '100%', height: '100%' },
  bgImg: { resizeMode: 'cover' },

  container: {
    flex: 1,
    backgroundColor: 'transparent',
    paddingTop: 40,
    ...(Platform.OS === 'web' && {
      width: '100%',
      maxWidth: 480,
      alignSelf: 'center',
    }),
  },
  header: {
    fontSize: 24,
    fontWeight: '800',
    textAlign: 'center',
    color: '#111',
    marginBottom: 12,
  },

  row: {
    justifyContent: 'space-between',
    marginBottom: 20,
    paddingHorizontal: 16,
  },
  listContent: {
    paddingBottom: 20,
  },
});

export default RewardsScreen;
