import React from 'react'
import {Switch, Route} from 'react-router-dom'
import {Home} from './Home'
import {SignUp} from './SignUp'
import {Reserve} from './Reserve'
import {Track} from './Track'
import {Report} from './Report'


export class Main extends React.Component{
    render() {
        return(
            <div>
                <Switch>
                    <Route exact path="/" component={Home}/>
                    <Route path="/home" component={Home}/>
                    <Route path="/signup" component={SignUp}/>
                    <Route path="/reserve" component={Reserve}/>
                    <Route path="/track" component={Track}/>
                    <Route path="/report" component={Report}/>
                </Switch>
            </div>
        )
    }
}
