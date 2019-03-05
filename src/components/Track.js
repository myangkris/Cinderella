import React from 'react'
import {Header} from './Header'
import {Footer} from './Footer'
import Countdown from 'antd/lib/statistic/Countdown'



export class Track extends React.Component {

    state={
        deadline: ''
    }

    componentDidMount() {
        this.setState({
            deadline: Date.now() + 51000
        })
    }

    render() {
        return(
            <div>
                <Header/>
                <div className="countdown">
                    <Countdown title="Your laundry will be finished in:" value={this.state.deadline} />
                </div>
                <Footer/>
            </div>
        )
    }
}