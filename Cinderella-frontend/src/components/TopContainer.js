import React from 'react'
import {Login} from './Login'
import LogoTextWhite from '../assets/design_elements/logo text - white.png'
import HowImage from '../assets/design_elements/home - image 1.png'
import Logo from '../assets/design_elements/logo 2 - white.png'
import Banner from '../assets/design_elements/banner text.png'

export class TopContainer extends React.Component {
    render() {
        return(
            <section className="top-container">
                <div className="sign-in-box">
                    <h1>Sign in with your</h1>
                    <img src={LogoTextWhite} alt=""/>
                    <h1>Account</h1>
                    <Login />
                    <img src={HowImage} alt=""/>
                </div>
                <header className="showcase">
                    <img src={Logo} alt=""/>
                    <img src={Banner} alt="" className="banner"/>
                </header>
            </section>
        )
    }
}