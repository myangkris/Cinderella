import React, {Component} from 'react';
import TrackCountdown from './TrackCountdown'


class TrackText extends Component {
    render() {
        return (
            <div className="confirm-text">
                <h1>Your Laundry Machine</h1>
                <div className="location">
                    <h3>Garden Plaza</h3>
                    <h3>25th Floor</h3>
                    <h3>No. {this.props.userInfo.currentId}</h3>
                </div>
                <p><strong>Your Washing will finish in</strong></p>
                <TrackCountdown />
            </div>
        );
    }
}

export default TrackText;