import React from 'react'
import {Header} from './Header'
import {Footer} from './Footer'
import {Content} from './Content'

export class Reserve extends React.Component {
    render() {
        return(
            <div>
                <Header/>
                <Content/>
                <Footer/>
            </div>
        )
    }
}
