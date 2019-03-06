import React from 'react'
import HighlightLogo from '../assets/design_elements/logo 2 - highlight.png'

export class AboutBanner extends React.Component{
    render() {
        return(
            <div className="about-banner">
                <h1>ABOUT</h1>
                <img src={HighlightLogo} alt=""/>
            </div>
        )
    }
}