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
        return this.props.isLoggedIn ? <Reserve handleLogout={this.props.handleLogout}/> : <Redirect to="/home"/>
    }

    getTrack = () => {
        return this.props.isLoggedIn ? <Track handleLogout={this.props.handleLogout}/> : <Redirect to="/home"/>
    }

    getConfirm = () => {
        return this.props.isLoggedIn ? <Confirm handleLogout={this.props.handleLogout}/> : <Redirect to="/home"/>
    }

    render() {
        return(
            <div>
                <Switch>
                    <Route exact path="/" component={this.getLogin}/>
                    <Route path="/home" component={this.getLogin}/>
                    <Route path="/register" component={Register}/>
                    <Route path="/reserve" component={this.getReserve}/>
                    <Route path="/track" component={this.getTrack}/>
                    <Route path="/report" component={Report}/>
                    <Route path="/confirm" component={this.getConfirm}/>
                </Switch>
            </div>
        )
    }
}
