import React from 'react'
import {Header} from './Header'
import {Footer} from './Footer'
import {MachineStatus} from './MachineStatus'

export class Reserve extends React.Component {
    state = {
        machineID: '',
        isAvailable: true,
        isOccupied: false,
        isMalfunctioned: false,
    }
    render() {
        return(
            <div>
                <Header/>
                <section className="status-wrapper">
                    <MachineStatus/>
                    <MachineStatus/>
                    <MachineStatus/>
                    <MachineStatus/>
                    <MachineStatus/>
                    <MachineStatus/>
                    <MachineStatus/>
                    <MachineStatus/>
                    <MachineStatus/>
                    <MachineStatus/>
                    <MachineStatus/>
                    <MachineStatus/>
                    <MachineStatus/>
                    <MachineStatus/>
                    <MachineStatus/>
                </section>
                <Footer/>
            </div>
        )
    }
}
