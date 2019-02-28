import React from 'react'
import HeaderLogo from '../assets/design_elements/logo 2.png'
import {MenuBar} from './MenuBar'
import {Icon} from 'antd'

export class Header extends React.Component {
    render() {
        return(
            <section className="header-container">
                <div className="logo-container">
                    <img src={HeaderLogo} alt=""/>
                </div>
                <div className="menu-container">
                    <MenuBar handleLogout={this.props.handleLogout}/>
                    <a className="logout" onClick={this.props.handleLogout}>
                        <Icon type="logout"/>{' '}Logout
                    </a>
                </div>
            </section>
        )
    }
}