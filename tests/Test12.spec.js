import { Selector,ClientFunction } from 'testcafe';
import page from './method.po';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();
    const slider  = Selector('a').withExactText('Slider');
    await t.click(slider);
});

const slide = Selector("#slider > span");

test('Slider below minimum value check', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame')
    .drag(slide, -100, 0 );
    const left =await  slide.getStyleProperty('left');
    await t.expect(left).eql('0px');
})
test('Slider start value check', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame')
    .expect(slide.exists).ok()
    .click(slide)
    const left =await slide.getStyleProperty('left');
    await t.expect(left).eql('0px');
})
test('Slider medium check', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame')
    .drag(slide, 300, 0 );
    const left =await slide.getStyleProperty('left');
    console.log(left);
    await t.expect(left).eql('301.562px');
})
test('Slider maximum value check', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame')
    .drag(slide, 900, 0 );
    const left =await slide.getStyleProperty('left');
    console.log(left);
    await t.expect(left).eql('569px');
})
