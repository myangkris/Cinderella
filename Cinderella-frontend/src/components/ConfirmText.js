import React, {Component} from 'react';
import {Button} from 'antd'

class ConfirmText extends Component {
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
                <Button>Confirm</Button>
            </div>
        );
    }
}

export default ConfirmText;