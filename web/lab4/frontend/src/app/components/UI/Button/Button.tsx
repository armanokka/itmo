import cs from './Button.module.css'

export default function Button() {
    return (
        <div className={cs.selector}>
            <div className={cs.selector_inner}>
                <p>R</p>
                <div className={cs.checkboxes}>
                    <label htmlFor="radio1">1</label>
                    <input type="radio" value={1} name="checkbox" id="radio1"/>
                    <label htmlFor="radio1">2</label>
                    <input type="radio" value={2} name="checkbox" id="radio1"/>
                    <label htmlFor="radio1">3</label>
                    <input type="radio" value={3} name="checkbox" id="radio1"/>
                    <label htmlFor="radio1">4</label>
                    <input type="radio" value={4} name="checkbox" id="radio1"/>
                    <label htmlFor="radio1">5</label>
                    <input type="radio" value={5} name="checkbox" id="radio1"/>
                </div>
            </div>
        </div>
    )
}
