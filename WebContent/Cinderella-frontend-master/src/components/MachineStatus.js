import React from 'react'
import Available from '../assets/design_elements/machine - available.png'
import Occupied from '../assets/design_elements/machine - in use.png'
import Malfunction from '../assets/design_elements/machine - fixing.png'


export class MachineStatus extends React.Component {
    state = {
        machineID: '',
        isAvailable: true,
        isOccupied: false,
        isMalfunctioned: false,
    }

    getMachineStatus = () => {
        const {isAvailable, isMalfunctioned, isOccupied } = this.state
        if (isAvailable) {
            return <img src={Available} alt=""/>
        } else if (isOccupied) {
            return <img src={Occupied} alt=""/>
        } else if (isMalfunctioned) {
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