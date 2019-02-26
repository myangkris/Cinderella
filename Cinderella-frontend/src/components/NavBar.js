import React from 'react'

export class NavBar extends React.Component {
    render() {
        return(
            <nav className="main-nav">
                <ul>
                    <li><a href="">Sign Up</a></li>
                    <li><a href="">How It Works</a></li>
                    <li><a href="">About</a></li>
                </ul>
            </nav>
        )
    }
}