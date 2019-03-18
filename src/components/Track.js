import React, { Component } from 'react';
import {Header} from './Header'
import {Footer} from './Footer'
import {UserContext} from './App'
import TrackMachine from './TrackMachine'
import TrackText from './TrackText'



export class Track extends Component {

    render() {

        return(
            <div>
                <UserContext.Consumer>
                    {userInfo => (
                        <Header userInfo={userInfo} handleLogout={this.props.handleLogout}/>
                    )}
                </UserContext.Consumer>
                <div className="confirm-holder">
                    <UserContext.Consumer>
                        {userInfo => (
                            <TrackMachine userInfo={userInfo} />
                        )}
                    </UserContext.Consumer>
                    <UserContext.Consumer>
                        {userInfo => (
                            <TrackText userInfo={userInfo} />
                        )}
                    </UserContext.Consumer>
                </div>
                <Footer/>
            </div>
        )
    }
}
