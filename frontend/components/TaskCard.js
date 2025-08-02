// components/TaskCard.js
import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity, Image, Platform } from 'react-native';
import { BlurView } from 'expo-blur';

// Placeholder image (replace with your own)
// Place it under assets: ../assets/task-default.png
const DEFAULT_ICON = require('../assets/Lead/card1.png');

export default function TaskCard({
  task = {},              // Prevent undefined
  onComplete,
  containerStyle,
  glass = true,           // Enable blur effect
}) {
  const isWeb = Platform.OS === 'web';
  // Fields
  const title = task.title ?? task.name ?? task.heading ?? 'Task';
  const description = task.description ?? task.desc ?? task.subtitle ?? '';
  const points = task.points ?? task.pts ?? task.score ?? 0;
  const completed = !!task.completed;

  // Icon: supports local require or remote URL
  const iconSource =
    typeof task.icon === 'string'
      ? { uri: task.icon }
      : task.icon || DEFAULT_ICON;

  const CardShell = ({ children }) =>
    glass && !isWeb ? (
      <BlurView intensity={35} tint="light" style={styles.blur}>
        <View style={styles.glassOverlay}>{children}</View>
      </BlurView>
    ) : (
      <View style={[styles.plain, glass && styles.glassOverlay]}>{children}</View>
    );

  return (
    <View style={[styles.cardWrapper, containerStyle]}>
      <CardShell>
        <View style={styles.row}>
          {/* Left icon */}
          <View style={styles.iconWrapper}>
            <Image source={iconSource} style={styles.iconImg} />
          </View>

          {/* Content area */}
          <View style={styles.contentArea}>
            <Text style={styles.title} numberOfLines={2}>
              {title}{' '}
              {!!points && <Text style={styles.points}>+{points} pts</Text>}
            </Text>
            {!!description && (
              <Text style={styles.description} numberOfLines={2}>
                {description}
              </Text>
            )}
          </View>

          {/* Button area */}
          <View style={styles.actionArea}>
            <TouchableOpacity
              style={[styles.button, completed && styles.buttonDone]}
              onPress={!completed ? onComplete : undefined}
              disabled={completed}
              activeOpacity={0.9}
            >
              <Text style={completed ? styles.buttonDoneText : styles.buttonText}>
                {completed ? 'Done' : 'Go'}
              </Text>
            </TouchableOpacity>
          </View>
        </View>
      </CardShell>
    </View>
  );
}

const RADIUS = 24;

const styles = StyleSheet.create({
  cardWrapper: {
    borderRadius: RADIUS,
    overflow: 'hidden',
    marginBottom: 14,
    shadowColor: '#000',
    shadowOpacity: 0.12,
    shadowRadius: 12,
    shadowOffset: { width: 0, height: 6 },
    elevation: 6,
  },

  // —— Blur effect shell —— //
  blur: { width: '100%' },
  glassOverlay: {
    backgroundColor: 'rgba(255,255,255,0.18)',
    borderWidth: 1,
    borderColor: 'rgba(255,255,255,0.35)',
    borderRadius: RADIUS,
    padding: 12,
  },

  // —— Plain (non-blur) shell —— //
  plain: {
    backgroundColor: '#fff',
    borderRadius: RADIUS,
    padding: 12,
    width: '100%',
  },

  row: {
    flexDirection: 'row',
    alignItems: 'flex-start',
  },

  /* Left icon styles */
  iconWrapper: {
    width: 48,            // ← Adjust here for larger size
    height: 48,
    borderRadius: 12,
    overflow: 'hidden',
    backgroundColor: 'rgba(59,130,246,0.18)', // Transparent background for better PNG display
    marginRight: 12,
    marginTop: 10,
  },
  iconImg: {
    width: '100%',
    height: '100%',
    resizeMode: 'cover',  // Use 'contain' for full display
  },

  contentArea: {
    flex: 1,
  },
  title: {
    fontSize: 15,
    fontWeight: 'bold',
    marginBottom: 4,
    color: '#111',
  },
  points: {
    fontSize: 15,
    fontWeight: '800',
    color: '#10b981',
  },
  description: {
    fontSize: 13,
    color: '#374151',
  },
  actionArea: {
    justifyContent: 'center',
    marginLeft: 10,
  },
  button: {
    backgroundColor: '#fde047',
    paddingHorizontal: 12,
    paddingVertical: 6,
    borderRadius: 16,
    borderWidth: 1,
    borderColor: 'rgba(255,255,255,0.5)',
  },
  buttonText: {
    color: '#000',
    fontWeight: '700',
  },
  buttonDone: {
    backgroundColor: 'rgba(255,255,255,0.45)',
    borderColor: 'rgba(255,255,255,0.55)',
  },
  buttonDoneText: {
    color: '#111',
    fontWeight: '700',
  },
});
