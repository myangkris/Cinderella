import React from 'react'
import {Header} from './Header'
import {Footer} from './Footer'
import {MachineStatus} from './MachineStatus'
import {testMachineData} from '../TestData'
import Available from '../assets/design_elements/machine - available.png'
import Occupied from '../assets/design_elements/machine - in use.png'
import Malfunction from '../assets/design_elements/machine - fixing.png'
import {API_RT} from '../constants'

export class Reserve extends React.Component {

    state = {
        machineId: '',
        machineStatus: testMachineData,
    }

    componentDidMount() {
        this.getMachineData()
    }

    getMachineData() {
        let machineData = fetch(`${API_RT}/allWashMachines`, {method: 'GET'})
            .then((res) => {
                console.log(res)
                res.json()
                    .then((response) => {
                        console.log(response)
                        this.setState({
                            machineId: response.machineId,
                            machineStatus: response.machineStatus
                        })
                    })
            })
    }

    render() {

        const machines = this.state.machineStatus.map(machine =>
            <MachineStatus
                key={machine.MachineId}
                machineId={machine.MachineId}
                machineStatus={machine.status}
            />)

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
