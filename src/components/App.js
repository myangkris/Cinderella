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
    currentId: ""
  }

  handleSuccessfulLogin = (data) => {
    localStorage.setItem(TOKEN_KEY, data['Authorization']);
    localStorage.setItem("username", data.user_info.name )
    localStorage.setItem("email", data.user_info.email )
    localStorage.setItem("phoneNumber", data.user_info.phoneNumber )
    localStorage.setItem("userId", data.user_info.userId)
    this.setState({
      isLoggedIn: true,
      userId: localStorage.getItem("userId"),
      name: localStorage.getItem("username"),
      balance: data.user_info.balance,
      bonusPoints: data.user_info.bonusPoints,
      email: localStorage.getItem("email"),
      phoneNumber: localStorage.getItem("phoneNumber"),
    })
    console.log("USER_INFO_STATE: " + JSON.stringify(this.state))
  }

  handleLogout = () => {
    localStorage.clear();
    this.setState({ isLoggedIn: false });
  }

  reserve = (e) => {
    console.log(e.target.value)
    this.setState({
      currentId: e.target.value
    })
  }


  render() {
    const {
      userId, name, balance, bonusPoints, email, phoneNumber, currentId
    } = this.state

    return (
        <UserContext.Provider value = {{
          userId, name, balance, bonusPoints, email, phoneNumber, currentId
        }}>
          <Main
              handleSuccessfulLogin={this.handleSuccessfulLogin}
              isLoggedIn={this.state.isLoggedIn}
              handleLogout={this.handleLogout}
              reserve={this.reserve}
          />
        </UserContext.Provider>

    );
  }
}

export default App;
