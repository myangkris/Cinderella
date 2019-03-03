import React, {Component} from 'react';
import {Button, message} from 'antd'
import {Link} from 'react-router-dom'
import {Form} from "antd/lib/form";

class ConfirmText extends Component {
    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
                //localStorage.setItem('username', values.username)
                fetch(`http://localhost:8080/Cinderella/washing`, {
                    method: 'POST',
                    body: JSON.stringify({
                        washingDuration: 50000,
                        userId: 222,
                        machineId: 233,
                    }),
                }).then((response) => {
                    if (response.ok) {
                        return response.text();
                    }
                    throw new Error(response.statusText);
                }).then((data) => {
                    message.success('Reserve Success!');
                    console.log(data);
                    this.props.handleSuccessfulLogin(data)
                }).catch((e) => {
                    console.log(e);
                    message.error('Reserve Failed.');
                });
            }
        });
    }

    fetchData = () => {
        fetch(`http://localhost:8080/Cinderella/washing`, {
            method: "POST",
            dataType: "JSON",
            body: JSON.stringify({
                washingDuration: 50000,
                userId: 222,
                machineId: 233,
            }),
        })
            .then((resp) => {
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
        return (
            <div className="confirm-text">
                <h1>Confirm Reservation</h1>
                <div className="location">
                    <h3>Garden Plaza</h3>
                    <h3>25th Floor</h3>
                    <h3>No. 12</h3>
                </div>
                <p>Reserving from 10:30 pm, April 3, 2019 to 11:30 pm, April3, 2019</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cum cumque libero nobis quidem, tempora unde? Aperiam architecto delectus, deserunt dolore esse harum minima nobis non, odio omnis sapiente velit. Blanditiis corporis cupiditate est et illo laborum maiores minima pariatur porro!</p>
                <button onClick={this.fetchData}><Link to="/track">Confirm</Link></button>
            </div>
        );
    }
}

export default ConfirmText;