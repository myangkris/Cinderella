import React from 'react'
import HighlightLogo from '../assets/design_elements/logo 2 - highlight.png'

export class HowItWorksBanner extends React.Component{
    render() {
        return(
            <div className="how-it-works-banner">
                <img src={HighlightLogo} alt=""/>
                <h1>HOW IT WORKS</h1>
            </div>
        )
    }
}