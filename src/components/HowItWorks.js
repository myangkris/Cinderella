import React from 'react'
import DemoImage from'../assets/design_elements/demo-img.jpg'

export class HowItWorks extends React.Component{
    render() {
        return(
            <section className="how-it-works">
                <div className="how-to-reserve">
                    <h1>Select/Reserve your Machines</h1>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias amet aperiam asperiores atque dolor dolore dolores eligendi enim eos excepturi exercitationem facere fugit id incidunt inventore ipsam iusto maiores mollitia nisi nostrum obcaecati officiis placeat possimus quisquam quos, sequi tenetur ullam ut velit voluptatum. Accusamus, cumque delectus doloribus ea earum eveniet facilis laudantium minima modi nihil omnis quia rerum tempora tenetur vel veniam, voluptate voluptatem. Aspernatur consequatur corporis optio praesentium quas? Est exercitationem in minus necessitatibus quae quas tempora, vitae.
                    </p>
                </div>
                <div className="how-to-reserve-demo">
                    <img src={DemoImage} alt=""/>
                </div>
                <div className="how-to-track-demo">
                    <img src={DemoImage} alt=""/>
                </div>
                <div className="how-to-track">
                    <h1>Track Progress and get notification when it's done</h1>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias amet aperiam asperiores atque dolor dolore dolores eligendi enim eos excepturi exercitationem facere fugit id incidunt inventore ipsam iusto maiores mollitia nisi nostrum obcaecati officiis placeat possimus quisquam quos, sequi tenetur ullam ut velit voluptatum. Accusamus, cumque delectus doloribus ea earum eveniet facilis laudantium minima modi nihil omnis quia rerum tempora tenetur vel veniam, voluptate voluptatem. Aspernatur consequatur corporis optio praesentium quas? Est exercitationem in minus necessitatibus quae quas tempora, vitae.
                    </p>
                </div>
                <div className="how-to-report">
                    <h1>Report malfunction</h1>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias amet aperiam asperiores atque dolor dolore dolores eligendi enim eos excepturi exercitationem facere fugit id incidunt inventore ipsam iusto maiores mollitia nisi nostrum obcaecati officiis placeat possimus quisquam quos, sequi tenetur ullam ut velit voluptatum. Accusamus, cumque delectus doloribus ea earum eveniet facilis laudantium minima modi nihil omnis quia rerum tempora tenetur vel veniam, voluptate voluptatem. Aspernatur consequatur corporis optio praesentium quas? Est exercitationem in minus necessitatibus quae quas tempora, vitae.
                    </p>
                </div>
                <div className="how-to-report-demo">
                    <img src={DemoImage} alt=""/>
                </div>
            </section>
        )
    }
}