// components/PointGraph.js
import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

export default function PointGraph({ data }) {
  return (
    <View style={styles.container}>
      {/* TODO: 这里用你选的图表库把 data 绘制成折线/柱状混合图 */}
      <Text>📈 Energy Chart Placeholder</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    height: 200,
    marginVertical: 8,
    backgroundColor: '#f0f0f0',
    borderRadius: 12,
    justifyContent: 'center',
    alignItems: 'center',
  },
});