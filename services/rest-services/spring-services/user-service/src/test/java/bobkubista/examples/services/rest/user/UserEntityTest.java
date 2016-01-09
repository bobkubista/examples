/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.user;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bob
 *
 */
public class UserEntityTest {

	/**
	 * Test method for
	 * {@link bobkubista.examples.services.rest.user.UserEntity#isAuthorized(bobkubista.examples.services.rest.user.Rights)}
	 * .
	 */
	@Test
	public void testIsAuthorizedWithRight() {
		final UserEntity user = this.mockUser(true, true, true);

		Assert.assertTrue(user.isAuthorized("right1"));
	}

	@Test
	public void testIsAuthorizedWithRole() {
		final UserEntity user = this.mockUser(true, true, true);

		Assert.assertTrue(user.isAuthorized("role"));
	}

	@Test
	public void testIsUnAuthorizedDifferentRight() {
		final UserEntity user = this.mockUser(true, true, true);

		Assert.assertFalse(user.isAuthorized("right2"));
	}

	@Test
	public void testIsUnAuthorizedRightNotActive() {
		final UserEntity user = this.mockUser(true, false, true);

		Assert.assertFalse(user.isAuthorized("right2"));
	}

	@Test
	public void testIsUnAuthorizedRoleNotActive() {
		final UserEntity user = this.mockUser(true, false, false);

		Assert.assertFalse(user.isAuthorized("right2"));
	}

	@Test
	public void testIsUnAuthorizedUserNotActive() {
		final UserEntity user = this.mockUser(false, false, false);

		Assert.assertFalse(user.isAuthorized("right2"));
	}

	private Rights mockRight(final boolean active) {
		final Rights right = new Rights();
		right.setFunctionalId("right1");
		right.setActive(active);
		return right;
	}

	private Roles mockRole(final boolean rightActive, final boolean roleActive) {
		final Roles role = new Roles();
		role.setActive(roleActive);
		role.setFunctionalId("role");
		role.getRights().add(this.mockRight(rightActive));
		return role;
	}

	private UserEntity mockUser(final boolean userActive, final boolean rightActive, final boolean roleActive) {
		final UserEntity user = new UserEntity();
		final Roles role = this.mockRole(rightActive, roleActive);
		user.getRoles().add(role);
		user.setActive(userActive);
		return user;
	}

}
