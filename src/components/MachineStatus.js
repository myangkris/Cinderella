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
    height: '3rem',
}

export class MachineStatus extends React.Component {

    getMachineStatus = () => {
        if (this.props.machineStatus === 0) {
            return <Popover placement="top"
                            content={<Link to="/confirm"><Button style={popover}
                                                onClick={this.props.reserve}
                                             value={this.props.machineId}>
                                Machine No.{this.props.machineId}
                                <br/>
                                Reserve me!
                            </Button></Link>} arrowPointAtCenter>
                <img src={Available} alt=""/>
            </Popover>
        } else if (this.props.machineStatus === 1) {
            return <img src={Occupied} alt=""/>
        } else if (this.props.machineStatus === 2) {
            return <img src={Malfunction} alt=""/>
        }
    }






    // fetchData = () => {
    //     fetch(`http://localhost:8080/Cinderella/washing`, {
    //         method: "POST",
    //         dataType: "JSON",
    //         body: JSON.stringify({
    //             washingDuration: 50000,
    //             userId: 1,
    //             machineId: this.props.machineID,
    //         }),
    //     })
    //         .then((resp) => {
    //             return resp.json()
    //         })
    //         .then((data) => {
    //             this.setState({ suggestion: data.suggestion })
    //         })
    //         .catch((error) => {
    //             console.log(error, "catch the hoop")
    //         })
    // }

    render() {

        return(
            <section className="machine-wrapper">
                {this.getMachineStatus()}
            </section>
        )
    }
}
