import React from 'react'
import {Header} from './Header'
import {Footer} from './Footer'
import {MachineStatus} from './MachineStatus'
import Available from '../assets/design_elements/machine - available.png'
import Occupied from '../assets/design_elements/machine - in use.png'
import Malfunction from '../assets/design_elements/machine - fixing.png'

export class Reserve extends React.Component {

    state = {
        machineStatus: []
    }

    componentDidMount ()  {
        fetch(`http://localhost:8080/Cinderella/allWashMachines`, {
            method: 'POST',
        })
            .then(res => res.json())
            .then(data => {
                console.log(data)
                this.setState({
                    machineStatus: data,
                })
                console.log("machineStatus STATE IN SetState: " + this.state.machineStatus)
            })

    }

    render() {
        // console.log("user_info PROPS IN RESERVE: " + this.props.user_info)
        // console.log("machineStatus PROPS IN RESERVE: " + this.props.machineStatus)
        console.log("machineStatus STATE IN RESERVE: " + this.state.machineStatus)
        const machines = this.state.machineStatus.map(machine =>
            <MachineStatus key={machine.MachineID} machineID={machine.MachineID}
                           machineStatus={machine.status}/>)

        return(
            <div>
                <Header handleLogout={this.props.handleLogout}/>
                <div className="legend-icon">
                    < img src={Available} alt=""/><p>Available</p >
                    < img src={Occupied} alt=""/><p>In use</p >
                    < img src={Malfunction} alt=""/><p>Cinderella is fixing it!</p >
                </div>
                <section className="status-wrapper">
                    {machines}
                </section>
                <Footer/>
            </div>
        )
    }
}