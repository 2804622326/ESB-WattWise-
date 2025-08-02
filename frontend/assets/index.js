// assets/index.js

/**
 * Centralized management of all image resources
 * Usage: import { Images } from '../assets';
 * The path is relative to assets/index.js
 */
export const Images = {
  // Icons used in HomeScreen
  mapPin:   require('./HomeScreen/map-pin.png'),
  bell:     require('./HomeScreen/bell.png'),
  myHomeBg:     require('./HomeScreen/my-home-bg.png'),
  communitybg:     require('./HomeScreen/community-bg.png'),
  lightning: require('./HomeScreen/lightning.png'),
  star:      require('./HomeScreen/star.png'),
  //goButton: require('./HomeScreen/go-button.png'),
  // Add more icons as needed in the following format:
  // someIcon: require('./SomeFolder/some-icon.png'),
};