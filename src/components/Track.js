import React, { Component } from 'react';
import {Header} from './Header'
import {Footer} from './Footer'
import {UserContext} from './App'



export class Track extends Component {

    state = {
        hours: 0,
        min: 0,
        sec: 0,
    }

    componentDidMount() {
        // update every second
        this.interval = setInterval(() => {
            const date = this.calculateCountdown(localStorage.getItem('endTime'));
            date ? this.setState(date) : this.stop();
        }, 1000);
    }

    componentWillUnmount() {
        this.stop();
    }

    calculateCountdown(endDate) {
        let diff = (endDate) - Date.parse(new Date()) / 1000;
        console.log(endDate)
        console.log(diff)


        // clear countdown when date is reached
        if (diff <= 0) return false;

        const timeLeft = {
            hours: 0,
            min: 0,
            sec: 0,
            millisec: 0,
        };

        // calculate time difference between now and expected date
        // if (diff >= (365.25 * 86400)) { // 365.25 * 24 * 60 * 60
        //     timeLeft.years = Math.floor(diff / (365.25 * 86400));
        //     diff -= timeLeft.years * 365.25 * 86400;
        // }
        // if (diff >= 86400) { // 24 * 60 * 60
        //     timeLeft.days = Math.floor(diff / 86400);
        //     diff -= timeLeft.days * 86400;
        // }
        if (diff >= 3600) { // 60 * 60
            timeLeft.hours = Math.floor(diff / 3600);
            diff -= timeLeft.hours * 3600;
        }
        if (diff >= 60) {
            timeLeft.min = Math.floor(diff / 60);
            diff -= timeLeft.min * 60;
        }
        timeLeft.sec = diff;

        return timeLeft;
    }

    stop() {
        clearInterval(this.interval);
    }

    addLeadingZeros(value) {
        value = String(value);
        while (value.length < 2) {
            value = '0' + value;
        }
        return value;
    }

    render() {

        const countDown = this.state;

        return(
            <div>
                <UserContext.Consumer>
                    {userInfo => (
                        <Header userInfo={userInfo} handleLogout={this.props.handleLogout}/>
                    )}
                </UserContext.Consumer>
                <div className="Countdown">
                    <span className="Countdown-col">
                      <span className="Countdown-col-element">
                        <strong>{this.addLeadingZeros(countDown.hours)}</strong>
                        <span>Hours</span>
                      </span>
                    </span>
                    <span className="Countdown-col">
                      <span className="Countdown-col-element">
                        <strong>{this.addLeadingZeros(countDown.min)}</strong>
                        <span>Min</span>
                      </span>
                    </span>
                    <span className="Countdown-col">
                      <span className="Countdown-col-element">
                        <strong>{this.addLeadingZeros(countDown.sec)}</strong>
                        <span>Sec</span>
                      </span>
                    </span>
                </div>
                <Footer/>
            </div>
        )
    }
}
