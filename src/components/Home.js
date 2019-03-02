import React from 'react'
import {TopContainer} from './TopContainer'
import {HowItWorks} from './HowItWorks'
import {HowItWorksBanner} from './HowItWorksBanner'
import {AboutBanner} from './AboutBanner'
import {Footer} from './Footer'

export class Home extends React.Component {
    render() {
        return(
            <div className="wrapper">
                <TopContainer handleSuccessfulLogin={this.props.handleSuccessfulLogin}/>
                {/*<NavBar/>*/}
                <HowItWorksBanner/>
                <HowItWorks/>
                <AboutBanner/>
                <Footer/>
            </div>
        )
    }
}
