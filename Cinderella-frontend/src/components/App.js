import React, { Component } from 'react';
import '../styles/App.css';
import 'antd/dist/antd.css';
import {Main} from './Main'
import {TOKEN_KEY} from '../constants'

class App extends Component {

  state = {
    isLoggedIn: Boolean(localStorage.getItem(TOKEN_KEY)),
  }

  handleSuccessfulLogin = (token) => {
    localStorage.setItem(TOKEN_KEY, token);
    this.setState({ isLoggedIn: true });
  }

  handleLogout = () => {
    localStorage.removeItem(TOKEN_KEY);
    this.setState({ isLoggedIn: false });
  }

  render() {
    return (
      <div>
        <Main
            handleSuccessfulLogin={this.handleSuccessfulLogin}
            isLoggedIn={this.state.isLoggedIn}
            handleLogout={this.handleLogout}
        />
      </div>
    );
  }
}

export default App;
