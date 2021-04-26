import {
    Landing,
    Tickets
} from '../components/pages/index.js'

const routes = [
    {
        path: "/",
        name: "Home",
        exact: true,
        component: <Landing />,
    },
    {
        path: "/tickets",
        name: "View Tickets",
        exact: true,
        component: <Tickets />,
    }
]

export default routes