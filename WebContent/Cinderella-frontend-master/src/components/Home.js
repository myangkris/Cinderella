import React from 'react'
import {TopContainer} from './TopContainer'
import {NavBar} from './NavBar'
import {HowItWorks} from './HowItWorks'
import {HowItWorksBanner} from './HowItWorksBanner'
import {AboutBanner} from './AboutBanner'
import {Footer} from './Footer'

export class Home extends React.Component {
    render() {
        return(
            <div className="wrapper">
                <TopContainer/>
                {/*<NavBar/>*/}
                <HowItWorksBanner/>
                <HowItWorks/>
                <AboutBanner/>
                <Footer/>
            </div>
        )
    }
}
