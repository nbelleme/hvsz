import { HvszWebPage } from './app.po';

describe('hvsz-web App', () => {
  let page: HvszWebPage;

  beforeEach(() => {
    page = new HvszWebPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
