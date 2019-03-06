import React, { Component } from 'react';
import '../styles/App.css';
import 'antd/dist/antd.css';
import {Main} from './Main'
import {TOKEN_KEY} from '../constants'

export const UserContext = React.createContext()

class App extends Component {

  state = {
    isLoggedIn: Boolean(localStorage.getItem(TOKEN_KEY)),
    userId: "",
    name: "",
    balance: "",
    bonusPoints: "",
    email: "",
    phoneNumber: "",
  }

  handleSuccessfulLogin = (data) => {
    console.log(data)
    localStorage.setItem(TOKEN_KEY, data['Authorization']);
    this.setState({
      isLoggedIn: true,
      userId: data.user_info.userId,
      name: data.user_info.name,
      balance: data.user_info.balance,
      bonusPoints: data.user_info.bonusPoints,
      email: data.user_info.email,
      phoneNumber: data.user_info.phoneNumber,
    })
    console.log("USER_INFO_STATE: " + JSON.stringify(this.state))
  }

  handleLogout = () => {
    localStorage.removeItem(TOKEN_KEY);
    this.setState({ isLoggedIn: false });
  }


  render() {
    const {
      userId, name, balance, bonusPoints, email, phoneNumber
    } = this.state

    return (
        <UserContext.Provider value = {{
          userId, name, balance, bonusPoints, email, phoneNumber
        }}>
          <Main
              handleSuccessfulLogin={this.handleSuccessfulLogin}
              isLoggedIn={this.state.isLoggedIn}
              handleLogout={this.handleLogout}
          />
        </UserContext.Provider>

    );
  }
}

export default App;
