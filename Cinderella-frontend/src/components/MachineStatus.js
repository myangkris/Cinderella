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
        <Button style={popover}>Reserve me!</Button>
);


export class MachineStatus extends React.Component {

    getMachineStatus = () => {
        if (this.props.machineStatus === 0) {
            return <Popover placement="top" content={content} arrowPointAtCenter><img src={Available} alt=""/></Popover>
        } else if (this.props.machineStatus === 1) {
            return <img src={Occupied} alt=""/>
        } else if (this.props.machineStatus === 2) {
            return <img src={Malfunction} alt=""/>
        }
    }

    handleClick() {
        console.log(this.props.machinId)
    }

    render() {

        return(
            <section className="machine-wrapper">
                {this.getMachineStatus()}
            </section>
        )
    }
}