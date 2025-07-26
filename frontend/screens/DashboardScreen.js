import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet, StatusBar } from 'react-native';

// WelcomeScreen serves as the app's entry point with a clean and elegant layout
export default function WelcomeScreen({ navigation }) {
  return (
    <View style={styles.container}>
      <StatusBar barStyle="light-content" backgroundColor="#4E4CB8" />
      
      {/* App Title */}
      <Text style={styles.heading}>Bienvenue!</Text>
      <Text style={styles.subheading}>Your personal French vocabulary book</Text>
      
      {/* Start Button */}
      <TouchableOpacity 
        style={styles.button} 
        onPress={() => navigation.replace('WordList')}
      >
        <Text style={styles.buttonText}>Start Learning</Text>
      </TouchableOpacity>
    </View>
  );
}

// Stylesheet for the WelcomeScreen
const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#4E4CB8', // Purple gradient-style background
    alignItems: 'center',
    justifyContent: 'center',
    paddingHorizontal: 24
  },
  heading: {
    fontSize: 36,
    fontWeight: '700',
    color: '#FFFFFF',
    marginBottom: 12
  },
  subheading: {
    fontSize: 18,
    color: '#E0E0E0',
    marginBottom: 40,
    textAlign: 'center'
  },
  button: {
    backgroundColor: '#FFFFFF',
    paddingVertical: 14,
    paddingHorizontal: 32,
    borderRadius: 30,
    shadowColor: '#000',
    shadowOpacity: 0.15,
    shadowOffset: { width: 0, height: 4 },
    shadowRadius: 8,
    elevation: 5
  },
  buttonText: {
    color: '#4E4CB8',
    fontSize: 18,
    fontWeight: '600'
  }
});
