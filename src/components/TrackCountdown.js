import React, {Component} from 'react';
import { Spin, Icon } from 'antd';

const antIcon = <Icon type="loading" style={{ padding: '10px', fontSize: 48 }} spin />;


class TrackCountdown extends Component {
    state = {
        hours: antIcon,
        min: antIcon,
        sec: antIcon,
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


        // clear countdown when date is reached
        if (diff <= 0) return false;

        const timeLeft = {
            hours: "0",
            min: "0",
            sec: "0",
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

        if (timeLeft.sec < 10 && timeLeft.sec >= 0) {
            timeLeft.sec = "0" + timeLeft.sec
        }
        if (timeLeft.min < 10 && timeLeft.min >= 0) {
            timeLeft.min = "0" + timeLeft.min
        }
        if (timeLeft.hours < 10 && timeLeft.hours >= 0) {
            timeLeft.hours = "0" + timeLeft.hours
        }

        return timeLeft;
    }

    stop() {
        clearInterval(this.interval);
        this.setState({
            hours: antIcon,
            min: antIcon,
            sec: antIcon,
        })
    }

    addLeadingZeros(value) {
        console.log(value)
        while (value.length < 2) {
            value = '0' + value;
        }
        return value;
    }

    render() {

        const countDown = this.state;

        return(
            this.calculateCountdown() ?
            <div className="Countdown">
                <span className="Countdown-col">
                  <span className="Countdown-col-element">
                    <strong>{this.addLeadingZeros(countDown.hours)}</strong>
                    <span>:</span>
                  </span>
                </span>
                <span className="Countdown-col">
                  <span className="Countdown-col-element">
                    <strong>{this.addLeadingZeros(countDown.min)}</strong>
                    <span>:</span>
                  </span>
                </span>
                <span className="Countdown-col">
                  <span className="Countdown-col-element">
                    <strong>{this.addLeadingZeros(countDown.sec)}</strong>
                  </span>
                </span>
            </div> :
                <h1>Your laundry is done!</h1>
        );
    }
}

export default TrackCountdown;