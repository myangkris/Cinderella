import React from 'react'
import {Header} from './Header'
import {Footer} from './Footer'
import {MachineStatus} from './MachineStatus'

export class Reserve extends React.Component {

    state = {
        machineID: {},
        machineStatus: {},
    }

    render() {
        return(
            <div>
                <Header/>
                <section className="status-wrapper">
                    <MachineStatus machineID={'1'} machineStatus={'isAvailable'}/>
                    <MachineStatus machineStatus={'isOccupied'}/>
                    <MachineStatus machineStatus={'isAvailable'}/>
                    <MachineStatus machineStatus={'isMalfunctioned'}/>
                    <MachineStatus machineStatus={'isAvailable'}/>
                    <MachineStatus machineStatus={'isOccupied'}/>
                    <MachineStatus machineStatus={'isAvailable'}/>
                    <MachineStatus machineStatus={'isAvailable'}/>
                    <MachineStatus machineStatus={'isAvailable'}/>
                    <MachineStatus machineStatus={'isOccupied'}/>
                    <MachineStatus machineStatus={'isAvailable'}/>
                    <MachineStatus machineStatus={'isAvailable'}/>
                    <MachineStatus machineStatus={'isOccupied'}/>
                    <MachineStatus machineStatus={'isAvailable'}/>
                </section>
                <Footer/>
            </div>
        )
    }
}
