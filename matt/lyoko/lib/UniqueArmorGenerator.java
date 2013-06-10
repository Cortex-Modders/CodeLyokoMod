package matt.lyoko.lib;

public class UniqueArmorGenerator
{
	public static int getUniquePlayerIdentifier(String username)
	{
		int identifier = 1;
		for(int i = 0; i < username.length(); i++)
		{
			identifier *= Integer.valueOf(username.substring(i, i + 1));
		}
		return identifier;
	}
}