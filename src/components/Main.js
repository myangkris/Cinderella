import React from 'react'
import {Switch, Route, Redirect} from 'react-router-dom'
import {Home} from './Home'
import {Register} from './Register'
import {Reserve} from './Reserve'
import {Track} from './Track'
import {Report} from './Report'
import 'antd/dist/antd.css';
import {Confirm} from './Confirm'


export class Main extends React.Component{

    getLogin = () => {
        return this.props.isLoggedIn ? <Redirect to="/reserve"/> : <Home
            handleSuccessfulLogin={this.props.handleSuccessfulLogin}/>
    }

    getReserve = () => {
        console.log("MAIN user_info: " + this.props.user_info)
        console.log("MAIN machine_list: " + this.props.machineStatus)
        return this.props.isLoggedIn ? <Reserve handleLogout={this.props.handleLogout}
                                                user_info={this.props.user_info}
                                                machineStatus={this.props.machineStatus}/> : <Redirect to="/home"/>
    }

    render() {
        return(
            <div>
                <Switch>
                    <Route exact path="/" component={this.getLogin}/>
                    <Route path="/home" component={this.getLogin}/>
                    <Route path="/register" component={Register}/>
                    <Route path="/reserve" component={this.getReserve}/>
                    <Route path="/track" component={Track}/>
                    <Route path="/report" component={Report}/>
                    <Route path="/confirm" component={Confirm}/>
                </Switch>
            </div>
        )
    }
}
