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
                    <UserContext.Consumer>
                        {userInfo => (
                            <ConfirmMachine userInfo={userInfo} />
                        )}
                    </UserContext.Consumer>
                    <UserContext.Consumer>
                        {userInfo => (
                            <ConfirmText userInfo={userInfo} />
                        )}
                    </UserContext.Consumer>
                </section>
                <Footer/>
            </div>
        )
    }
}
