import React, {Component} from 'react';
import Machine from '../assets/design_elements/machine - available.png'


class ConfirmMachine extends Component {


    render() {


        return (
            <div className="confirm-machine">
                <img src={Machine} alt=""/>
                <h3>Garden Plaza</h3>
                <h3>25th Floor</h3>
                <h3>Machine No.{this.props.userInfo.currentId}</h3>
            </div>
        );
    }
}

export default ConfirmMachine;