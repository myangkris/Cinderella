import React from 'react'
import Available from '../assets/design_elements/machine - available.png'
import Occupied from '../assets/design_elements/machine - in use.png'
import Malfunction from '../assets/design_elements/machine - fixing.png'
import { Popover, Button } from 'antd';
import {Link} from 'react-router-dom'

const popover = {
    color: '#fff',
    border: 'none',
    backgroundColor: '#048ba8',
}

const content = (
        <Button style={popover}><Link to="/confirm">Reserve me!</Link></Button>
);


export class MachineStatus extends React.Component {

    getMachineStatus = () => {
        if (this.props.machineStatus == 'isAvailable') {
            return <Popover placement="top" content={content} arrowPointAtCenter><img src={Available} alt=""/></Popover>
        } else if (this.props.machineStatus == 'isOccupied') {
            return <img src={Occupied} alt=""/>
        } else if (this.props.machineStatus == 'isMalfunctioned') {
            return <img src={Malfunction} alt=""/>
        }
    }

    render() {

        return(
            <section className="machine-wrapper">
                {this.getMachineStatus()}
            </section>
        )
    }
}