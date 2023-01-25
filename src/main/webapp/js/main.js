import router from './router.js';
import chatComp from './components/chat.js';
import home from "./components/home.js";

router.register('/', home);
router.register('/home', home);
router.register('/chat', chatComp);

router.navigate('/');
