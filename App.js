/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { Component } from 'react';
import {
  View,
  Button,
  NativeModules,
} from 'react-native';

export default class App extends Component {
  onPressButton = () => {
    NativeModules.BaseJSBridgeAndroid.testAndroidToast('调用android端方法');
  }
  render() {
    return (
      <View style={{ flex: 1 }}>
        <Button title="调用android的方法" onPress={this.onPressButton}>

        </Button>
      </View>
    );
  }
}
