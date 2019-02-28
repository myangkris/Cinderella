import React from 'react'
import {Header} from './Header'
import {Footer} from './Footer'
import {MachineStatus} from './MachineStatus'
import {testMachineData} from '../TestData'
import Available from '../assets/design_elements/machine - available.png'
import Occupied from '../assets/design_elements/machine - in use.png'
import Malfunction from '../assets/design_elements/machine - fixing.png'

export class Reserve extends React.Component {

    state = {
        machineStatus: testMachineData
    }

    render() {

        const machines = this.state.machineStatus.map(machine =>
            <MachineStatus machineID={machine.MachineId} machineStatus={machine.status}/>)

        return(
            <div>
                <Header handleLogout={this.props.handleLogout}/>
                <div className="legend-icon">
                    <img src={Available} alt=""/><p>Available</p>
                    <img src={Occupied} alt=""/><p>In use</p>
                    <img src={Malfunction} alt=""/><p>Cinderella is fixing it!</p>
                </div>
                <section className="status-wrapper">
                    {machines}
                </section>
                <Footer/>
            </div>
        )
    }
}
