import React from 'react'
import {Switch, Route} from 'react-router-dom'
import {Home} from './Home'
import {Register} from './Register'
import {Reserve} from './Reserve'
import {Track} from './Track'
import {Report} from './Report'
import 'antd/dist/antd.css';
import {Confirm} from './Confirm'



export class Main extends React.Component{
    render() {
        return(
            <div>
                <Switch>
                    <Route exact path="/" component={Home}/>
                    <Route path="/home" component={Home}/>
                    <Route path="/register" component={Register}/>
                    <Route path="/reserve" component={Reserve}/>
                    <Route path="/track" component={Track}/>
                    <Route path="/report" component={Report}/>
                    <Route path="/confirm" component={Confirm}/>
                </Switch>
            </div>
        )
    }
}
