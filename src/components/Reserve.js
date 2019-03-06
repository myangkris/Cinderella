import React, {useContext} from 'react'
import {Header} from './Header'
import {Footer} from './Footer'
import {MachineStatus} from './MachineStatus'
import Available from '../assets/design_elements/machine - available.png'
import Occupied from '../assets/design_elements/machine - in use.png'
import Malfunction from '../assets/design_elements/machine - fixing.png'
import {UserContext} from './App'

export class Reserve extends React.Component {

    state = {
        machineStatus: [],
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
            })

    }


    render() {
        const machines = this.state.machineStatus.map(machine =>
            <UserContext.Consumer>
                {userInfo => (
                    <MachineStatus userInfo={userInfo} key={machine.id} machineId={machine.id}
                                   machineStatus={machine.status}/>
                )}
            </UserContext.Consumer>
        )


        return(
            <div>
                <UserContext.Consumer>
                    {userInfo => (
                        <Header userInfo={userInfo} handleLogout={this.props.handleLogout}/>
                    )}
                </UserContext.Consumer>
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