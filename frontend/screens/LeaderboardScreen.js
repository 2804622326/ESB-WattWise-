import React, { useState, useEffect } from 'react';
import { View, Text, Button, FlatList, Alert, StyleSheet } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import * as Speech from 'expo-speech'; // Added: For speech functionality

// This screen displays a list of words with their translations, allows adding, editing, deleting, and pronouncing words.
export default function WordListScreen({ navigation }) {
  const [words, setWords] = useState([]);

  const loadWords = async () => {
    try {
      const data = await AsyncStorage.getItem('WORDS');
      if (data !== null) {
        setWords(JSON.parse(data));
      } else {
        setWords([]);
      }
    } catch (e) {
      console.error('Failed to load words:', e);
    }
  };

  useEffect(() => {
    const unsubscribe = navigation.addListener('focus', () => {
      loadWords();
    });
    return unsubscribe;
  }, [navigation]);

  /*
   * Handles the deletion of a word from the word list.
   * Displays a confirmation alert before proceeding with the deletion.
   * If confirmed, removes the word from the list and updates AsyncStorage.
   */
  const handleDelete = (id) => {
    Alert.alert(
      'Confirm Deletion',
      'Are you sure you want to delete this word?',
      [
        { text: 'Cancel', style: 'cancel' },
        {
          text: 'Confirm',
          onPress: async () => {
            try {
              const newList = words.filter(item => item.id !== id);
              setWords(newList);
              await AsyncStorage.setItem('WORDS', JSON.stringify(newList));
            } catch (e) {
              console.error('Failed to delete word:', e);
            }
          }
        }
      ]
    );
  };

  // ✅ Render each word item, including the "Speak" button
  const renderItem = ({ item }) => (
    <View style={styles.item}>
      <View style={styles.itemText}>
        <Text style={styles.wordText}>{item.word}</Text>
        <Text style={styles.translationText}>{item.translation}</Text>
      </View>
      <View style={styles.itemButtons}>
        {/* ✅ Speak button (click to pronounce) */}
        <Button
          title="🔊"
          onPress={() => Speech.speak(item.word, { language: 'fr-FR' })}
        />
        <Button
          title="Edit"
          onPress={() => navigation.navigate('WordForm', { word: item })}
        />
        <Button
          title="Delete"
          color="red"
          onPress={() => handleDelete(item.id)}
        />
      </View>
    </View>
  );

  return (
    <View style={styles.container}>
      <Button title="Add New Word" onPress={() => navigation.navigate('WordForm')} />
      <FlatList
        data={words}
        keyExtractor={item => item.id.toString()}
        renderItem={renderItem}
        style={styles.list}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16
  },
  list: {
    marginTop: 8
  },
  item: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    paddingVertical: 8,
    borderBottomWidth: 1,
    borderColor: '#ccc'
  },
  itemText: {
    flex: 1
  },
  wordText: {
    fontSize: 18,
    fontWeight: 'bold'
  },
  translationText: {
    fontSize: 16,
    color: '#555'
  },
  itemButtons: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'flex-end',
    gap: 4 // If gap is not supported, replace with marginLeft
  }
});
