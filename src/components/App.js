import React, { Component } from 'react';
import '../styles/App.css';
import 'antd/dist/antd.css';
import {Main} from './Main'
import {TOKEN_KEY} from '../constants'

class App extends Component {

  state = {
    isLoggedIn: Boolean(localStorage.getItem(TOKEN_KEY)),
    user_info: "",
    machineStatus: "",
  }

  handleSuccessfulLogin = (data) => {
    localStorage.setItem(TOKEN_KEY, data['Authorization']);
    localStorage.setItem("user_info", JSON.stringify(data['user_info']));
    localStorage.setItem("machine_list", data['machine_list']);
    console.log(data['machine_list'])
    console.log(localStorage.getItem(TOKEN_KEY))
    //this.setState({ isLoggedIn: true, user_info: data['user_info'], machine_list: data['machine_list']});
    this.setState({ isLoggedIn: true})
    this.setState({ user_info: data['user_info']})
    this.setState({ machineStatus: data['machine_list']})
    console.log("USER_INFO_STATE: " + JSON.stringify(this.state.user_info))
    console.log("MACHINE_LIST: " + JSON.stringify(this.state.machine_list))
  }

  handleLogout = () => {
    localStorage.removeItem(TOKEN_KEY);
    this.setState({ isLoggedIn: false });
  }

  render() {
    console.log("MACHINE_LIST_2: " + this.state.machine_list)
    console.log("MACHINE_LIST_2: " + JSON.stringify(this.state.machine_list))
    return (
      <div>
        <Main
            handleSuccessfulLogin={this.handleSuccessfulLogin}
            isLoggedIn={this.state.isLoggedIn}
            handleLogout={this.handleLogout}
            user_info={this.state.user_info}
            machineStatus={this.state.machineStatus}
        />
      </div>
    );
  }
}

export default App;
