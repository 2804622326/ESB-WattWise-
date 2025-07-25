import React, { useState } from 'react';
import { View, Text, TextInput, Button, Alert, StyleSheet } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';

export default function WordFormScreen({ route, navigation }) {
  const existingWord = route.params ? route.params.word : null; // 获取路由传递的单词 (get word passed from route params if any)
  // 使用 useState 钩子管理表单输入状态 (useState hooks to manage form inputs)
  const [word, setWord] = useState(existingWord ? existingWord.word : '');
  const [translation, setTranslation] = useState(existingWord ? existingWord.translation : '');
  const [example, setExample] = useState(existingWord ? existingWord.example : '');

  // 判断当前是添加还是编辑模式 (Determine if current mode is add or edit)
  const isEdit = existingWord !== null;

  // 保存单词 (添加或更新) 的函数 (Function to save the word - add or update)
  const saveWord = async () => {
    // 验证必填字段 (Validate required fields)
    if (!word || !translation) {
      Alert.alert('Incomplete', 'Please enter both word and translation.');
      return; // 如果必填没填则不继续 (stop if required fields are empty)
    }
    try {
      const data = await AsyncStorage.getItem('WORDS');
      const wordList = data ? JSON.parse(data) : [];
      if (isEdit) {
        // 更新现有单词 (Update existing word)
        const updatedList = wordList.map(item => {
          if (item.id === existingWord.id) {
            return { ...item, word: word, translation: translation, example: example };
          }
          return item;
        });
        await AsyncStorage.setItem('WORDS', JSON.stringify(updatedList));
      } else {
        // 添加新单词 (Add new word)
        const newEntry = {
          id: Date.now(), // 使用当前时间作为简易唯一ID (use current time as a simple unique ID)
          word: word,
          translation: translation,
          example: example
        };
        wordList.push(newEntry);
        await AsyncStorage.setItem('WORDS', JSON.stringify(wordList));
      }
      navigation.goBack(); // 返回上一页 (Go back to previous screen)
    } catch (e) {
      console.error('Error saving word:', e);
    }
  };

  return (
    <View style={styles.container}>
      {/* 表单标题 (Form title) 根据模式显示不同文本 (different text depending on mode) */}
      <Text style={styles.title}>{isEdit ? 'Edit Word' : 'Add New Word'}</Text>
      {/* 单词输入框 (Word input) */}
      <TextInput
        style={styles.input}
        placeholder="French Word"
        value={word}
        onChangeText={setWord}
      />
      {/* 翻译输入框 (Translation input) */}
      <TextInput
        style={styles.input}
        placeholder="Translation"
        value={translation}
        onChangeText={setTranslation}
      />
      {/* 例句输入框 (Example sentence input) 可选 (optional) */}
      <TextInput
        style={[styles.input, styles.exampleInput]}
        placeholder="Example sentence (optional)"
        value={example}
        onChangeText={setExample}
        multiline
      />
      {/* 保存按钮 (Save button) */}
      <Button
        title={isEdit ? 'Save Changes' : 'Add Word'}
        onPress={saveWord}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: '#fff'
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 20
  },
  input: {
    borderColor: '#ccc',
    borderWidth: 1,
    padding: 10,
    fontSize: 16,
    marginBottom: 15,
    borderRadius: 4
  },
  exampleInput: {
    height: 80
  }
});
