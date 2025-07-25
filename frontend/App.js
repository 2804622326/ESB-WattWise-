/**
 * New Features for CA2:
 * 1. Native Alert Dialog: Implemented using React Native's Alert API for delete confirmation.
 * 2. Text-to-Speech (TTS): Implemented using expo-speech to pronounce French words aloud.
 * These features are not covered in class and are added to enhance both usability and language learning support.
 */

import 'react-native-gesture-handler'; // Source: Required by react-navigation for gesture support
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

import WelcomeScreen from './WelcomeScreen';
import WordListScreen from './WordListScreen';
import WordFormScreen from './WordFormScreen';

const Stack = createStackNavigator(); // Create a Stack Navigator

export default function App() {
  return (
    <NavigationContainer>
      {/* Stack navigator container defining app navigation */}
      <Stack.Navigator initialRouteName="Welcome">
        {/* Welcome Screen with header hidden */}
        <Stack.Screen name="Welcome" component={WelcomeScreen} options={{ headerShown: false }} />
        {/* Word List Screen */}
        <Stack.Screen name="WordList" component={WordListScreen} options={{ title: 'My Vocabulary' }} />
        {/* Add/Edit Word Screen */}
        <Stack.Screen name="WordForm" component={WordFormScreen} options={{ title: 'Add/Edit Word' }} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
