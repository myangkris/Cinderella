import React from 'react'
import {Header} from './Header'
import {Footer} from './Footer'
import ConfirmMachine from './ConfirmMachine'
import ConfirmText from './ConfirmText'
import {UserContext} from './App'


export class Confirm extends React.Component {
    render() {
        return(
            <div>
                <UserContext.Consumer>
                    {userInfo => (
                        <Header userInfo={userInfo} handleLogout={this.props.handleLogout}/>
                    )}
                </UserContext.Consumer>
                <section className="confirm-holder">
                    <ConfirmMachine/>
                    <ConfirmText/>
                </section>
                <Footer/>
            </div>
        )
    }
}
