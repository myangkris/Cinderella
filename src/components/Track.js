import React from 'react'
import {Header} from './Header'
import {Footer} from './Footer'
import Countdown from 'antd/lib/statistic/Countdown'
import {UserContext} from './App'



export class Track extends React.Component {

    state={
        deadline: Date.now() + 51000
    }

    render() {
        return(
            <div>
                <UserContext.Consumer>
                    {userInfo => (
                        <Header userInfo={userInfo} handleLogout={this.props.handleLogout}/>
                    )}
                </UserContext.Consumer>
                <div className="countdown">
                    <Countdown title="Your laundry will be finished in:" value={this.state.deadline} />
                </div>
                <Footer/>
            </div>
        )
    }
}