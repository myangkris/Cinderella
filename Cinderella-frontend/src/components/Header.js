import React from 'react'
import HeaderLogo from '../assets/design_elements/logo 2.png'
import {MenuBar} from './MenuBar'

export class Header extends React.Component {
    render() {
        return(
            <section className="header-container">
                <div className="logo-container">
                    <img src={HeaderLogo} alt=""/>
                </div>
                <div className="menu-container">
                    <MenuBar/>
                </div>
            </section>
        )
    }
}