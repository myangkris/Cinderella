import React, {Component} from 'react';
import {Link} from 'react-router-dom'
import {API_ROOT} from '../constants'
import Clock from 'react-live-clock';


class ConfirmText extends Component {

    fetchData = () => {
        fetch(`${API_ROOT}/washing`, {
            method: "POST",
            dataType: "JSON",
            body: JSON.stringify({
                washingDuration: 50000,
                userId: localStorage.getItem("userId"),
                machineId: this.props.userInfo.currentId,
            }),
        })
            .then((resp) => {
                console.log(resp)
                return resp.json()
            })
            .then((data) => {
                this.setState({ suggestion: data.suggestion })
            })
            .catch((error) => {
                console.log(error, "catch the hoop")
            })
    }
    render() {

        const currentTimeDate = new Date()
        const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "November", "December"]
        const days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"]

        return (
            <div className="confirm-text">
                <h1>Confirm Reservation</h1>
                <div className="location">
                    <h3>Garden Plaza</h3>
                    <h3>25th Floor</h3>
                    <h3>No. {this.props.userInfo.currentId}</h3>
                </div>
                <p className="reserve-time">Reserving from <Clock format={'HH:mm:ss'} ticking={true} />, {days[currentTimeDate.getDay()]}, {months[currentTimeDate.getMonth()]} {currentTimeDate.getDate()}, {currentTimeDate.getFullYear()}, Estimate Washing Time is 50 Minutes</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cum cumque libero nobis quidem, tempora unde? Aperiam architecto delectus, deserunt dolore esse harum minima nobis non, odio omnis sapiente velit. Blanditiis corporis cupiditate est et illo laborum maiores minima pariatur porro!</p>
                <Link to="/track"><button className="confirm-button" onClick={this.fetchData}>Confirm</button></Link>
            </div>
        );
    }
}

export default ConfirmText;